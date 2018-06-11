job('Gettingserverrestart') {
label('label')
    steps {
        publishOverSsh {
            server('192.168.33.10') {
                transferSet {
                    execCommand('sudo syatemctl restart tomcat')
                }
            }
        }
    }
}
