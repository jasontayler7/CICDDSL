job('Gettingdeployment'){
  steps{
  shell('scp /home/vagrant/slave/workspace/taskworkspace/Spring3HibernateApp/target/Spring3HibernateApp.war vagrant@192.168.0.87:/opt/tomcat/webapps')
  }
}
