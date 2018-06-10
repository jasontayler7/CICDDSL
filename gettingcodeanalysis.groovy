mavenJob('Gettingcodeanalysis') {
  customWorkspace('/home/vagrant/slave/workspace/taskworkspace')
    jdk('java-8')
  goals('install') 
  goals('cobertura:cobertura')
       rootPOM("Spring3HibernateApp/pom.xml")
        mavenInstallation('mvn-3.5.3')
}