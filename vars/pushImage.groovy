def pushImage() {
    withCredentials([
        file(credentialsId: 'auth-service.env', variable: 'AUTH_ENV_FILE'),
        file(credentialsId: 'user-service.env', variable: 'USER_ENV_FILE'),
        file(credentialsId: 'captain-service.env', variable: 'CAPTAIN_ENV_FILE'),
        file(credentialsId: 'ride-service.env', variable: 'RIDE_ENV_FILE'),
        file(credentialsId: 'payment-service.env', variable: 'PAYMENT_ENV_FILE'),
        file(credentialsId: 'call-service.env', variable: 'CALL_ENV_FILE')
    ]) {
        sh '''
            cd Micro-Services
            docker compose push
        '''
    }
}