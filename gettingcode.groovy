job('Gettingcode') {
    customWorkspace('/home/vagrant/slave/workspace/taskworkspace/')

    scm {
        
      git{
        
        remote
        
         {
            url('https://github.com/Team-A-Ninja/ContinuousIntegration.git')
        
         }
            branch('master')  
         }
       }

    steps 
  
  		{      
          
		shell('ls -la' )
	
          }
}
