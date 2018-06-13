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
