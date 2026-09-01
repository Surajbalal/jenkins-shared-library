package com.example

class Docker implements Serializable{
    def script

    Docker(script){
        this.script = script
    }


    def buildImage(){
         script.withCredentials([
        script.file(credentialsId: 'auth-service.env', variable: 'AUTH_ENV_FILE'),
        script.file(credentialsId: 'user-service.env', variable: 'USER_ENV_FILE'),
        script.file(credentialsId: 'captain-service.env', variable: 'CAPTAIN_ENV_FILE'),
        script.file(credentialsId: 'ride-service.env', variable: 'RIDE_ENV_FILE'),
        script.file(credentialsId: 'payment-service.env', variable: 'PAYMENT_ENV_FILE'),
        script.file(credentialsId: 'call-service.env', variable: 'CALL_ENV_FILE')
    ]) {
        script.sh '''
            cd Micro-Services
            docker compose build
        '''
    }
    }

    def dockerLogin(){
         script.withCredentials([
                    script.usernamePassword(
                        credentialsId: 'Docker-login',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]){
                    script.sh '''
            echo "$DOCKER_PASSWORD" | docker login \
                --username "$DOCKER_USER" \
                --password-stdin
        '''
                }
    }

    def pushImage(){
        script.withCredentials([
        script.file(credentialsId: 'auth-service.env', variable: 'AUTH_ENV_FILE'),
        script.file(credentialsId: 'user-service.env', variable: 'USER_ENV_FILE'),
        script.file(credentialsId: 'captain-service.env', variable: 'CAPTAIN_ENV_FILE'),
        script.file(credentialsId: 'ride-service.env', variable: 'RIDE_ENV_FILE'),
        script.file(credentialsId: 'payment-service.env', variable: 'PAYMENT_ENV_FILE'),
        script.file(credentialsId: 'call-service.env', variable: 'CALL_ENV_FILE')
    ]) {
        script.sh '''
            cd Micro-Services
            docker compose push
        '''
    }
    }
    
}