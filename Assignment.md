# Tasks
## 1.Create an Organization in Github and add members.
![Organization created](/media/Organization.png) 

## 1.1. Add members in the Organization

![Members](/media/Members.png)  

## 2. Install Jenkins Role Based Plugin and assign different roles to different users.

### What is Jenkins Role based blugin?

#### It enables user authorization using a Role-Based strategy. Roles can be defined globally or for particular jobs or nodes selected by regular expressions. 

![Plugin](/media/rolebasedstrategyplugin.png)  
![Users](/media/Team_a_user.png)
![Manage and Assign Roles](media/rolepermissions.png)
![Assign Roles](/media/userrolepermission.png)  
  


==============================================================================================================================================

## 3 Clone git repo and perform CICD

### 3.1 Steps to be performed using DSL job

  - Clone the repo in custom directory
  - Check code Stability
  - Check code Quality
  - Check code Coverage
  - Create a Pipeline to perform above tasks and deploy code


#### 3.2 DSL to clone the repo in custom directory

job('Gettingcode') {  
label('vagrant')  
    customWorkspace('/home/vagrant/slave/workspace/taskworkspace/')  
    scm {  
      git{          
        remote  
	{    
         url('https://github.com/Team-A-Ninja/ContinuousIntegration.git')          
         }  
         branch('master')  
         }}  
	 steps{      
  shell('ls -la' ) 
}}      

----------------------------------------------------------------------------------------------------------------------------------------------

#### 3.3 DSL to Check code Stability


mavenJob('Gettingcodeanalysis') {  
label('vagrant')  
  customWorkspace('/home/vagrant/slave/workspace/taskworkspace')  
    jdk('java-8')  
  goals('clean compile')  
       rootPOM("Spring3HibernateApp/pom.xml")  
        mavenInstallation('mvn-3.5.3')  
}   


----------------------------------------------------------------------------------------------------------------------------------------------

#### 3.4 DSL to Check code Quality

mavenJob('Gettingcodeanalysis') {  
label('vagrant')  
  customWorkspace('/home/vagrant/slave/workspace/taskworkspace')  
    jdk('java-8')  
  goals('compile')  
    goals('findbugs:findbugs')  
  goals('checkstyle:checkstyle')  
       rootPOM("Spring3HibernateApp/pom.xml")  
        mavenInstallation('mvn-3.5.3')  
}  

----------------------------------------------------------------------------------------------------------------------------------------------

#### 3.5 DSL to Check code Coverage


mavenJob('Gettingcodeanalysis') {  
label('vagrant')  
  customWorkspace('/home/vagrant/slave/workspace/taskworkspace')  
    jdk('java-8')  
  goals('install')  
  goals('cobertura:cobertura')  
       rootPOM("Spring3HibernateApp/pom.xml")  
        mavenInstallation('mvn-3.5.3')  
}  

----------------------------------------------------------------------------------------------------------------------------------------------

#### 3.6 Deployment DSL

job('Gettingdeployment'){  
label('vagrant')  
  steps{  
  shell('scp /home/vagrant/slave/workspace/taskworkspace/Spring3HibernateApp/target/Spring3HibernateApp.war vagrant@192.168.0.87:/opt/tomcat/webapps')  
  }  
}  

----------------------------------------------------------------------------------------------------------------------------------------------

#### 3.7 DSL to restart server after deployment

job('Gettingserverrestart') {  
label('label')  
    steps {  
        publishOverSsh {  
            server('192.168.0.87') {  
                transferSet {  
                    execCommand('sudo syatemctl restart tomcat')  
                }  
            }  
        }  
    }  
}  

----------------------------------------------------------------------------------------------------------------------------------------------

## 3.8 Pipeline DSL for CD

node('vagrant'){  
    stage('getting code') {  
    build job: 'Gettingcode'  
    }  
    stage('codestability') {  
    build job: 'Gettingcodestability'  
    }  
    stage('codequality') {  
    build job: 'Gettingcodequality'  
    }  
    stage('code coverage') {  
    build job: 'Gettingcodeanalysis'  
    }  
    stage('deployement') {  
    build job: 'Gettingdeployment'  
    }  
    stage('serverrestart') {  
    build job: 'Gettingserverrestart'  
    }  
    
}  

![Pipeline output](/media/pipelineoutput.png)  
![](/media/pipelineoutput2.png)  
![Pipeline Status](/media/pipelinestatus.png)  

----------------------------------------------------------------------------------------------------------------------------------------------

## 4. Above task was performed by a master on slave machine

![Slave](/media/jobsonslave.png)  

---------------------------------------------------------------------------------------------------------------------------
## 5. Deployment

![Deployment](/media/deployement.png)  

--------------------------------------------------------------------------------------------------------------------------

## 
