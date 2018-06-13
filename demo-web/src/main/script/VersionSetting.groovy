import java.text.MessageFormat
def year = MessageFormat.format("{0,date,yyyy}", new Date())
def month = MessageFormat.format("{0,date,MM}", new Date())
def day = MessageFormat.format("{0,date,dd}", new Date())
def _year=1;
if(year.toInteger() > 2017){
    _year=(year.toInteger()-2017)+1
}else{
    _year=1
}

def version = (_year+"."+month.toInteger()+"."+day.toInteger()).toString()
def profile = pom.properties["profileActive"]
if(profile.contains("dev")){
    version+= "-SNAPSHOT";
}else if(profile.contains("test")){
    version+= "-TEST-RELEASE";
}
project.properties.setProperty('web.build.version', version)
project.properties['web.build.version'] = version

def input  = new File(project.basedir,"pom.xml")
def pomNode = new XmlParser(true,false).parse(input)
def oldVersion =  pomNode.version.text()
if(oldVersion != version){
    println "version:"+project.properties['web.build.version']
    pomNode.version[0].value = [ version ]
    input.writable = true
    def xmlNodePrinter = new XmlNodePrinter(new PrintWriter(input))
    xmlNodePrinter.preserveWhitespace = true
    xmlNodePrinter.expandEmptyElements = true
    xmlNodePrinter.print(pomNode)
}
