mavenJob('Gettingcodeanalysis') {
label('vagrant')
  customWorkspace('/home/vagrant/slave/workspace/taskworkspace')
    jdk('java-8')
  goals('clean compile') 
       rootPOM("Spring3HibernateApp/pom.xml")
        mavenInstallation('mvn-3.5.3')
}
