package com.example

class Docker implements serializable{
    def script

    Docker(script){
        this.script = script
    }


    def buildImage(){
         script.withCredentials([
        file(credentialsId: 'auth-service.env', variable: 'AUTH_ENV_FILE'),
        file(credentialsId: 'user-service.env', variable: 'USER_ENV_FILE'),
        file(credentialsId: 'captain-service.env', variable: 'CAPTAIN_ENV_FILE'),
        file(credentialsId: 'ride-service.env', variable: 'RIDE_ENV_FILE'),
        file(credentialsId: 'payment-service.env', variable: 'PAYMENT_ENV_FILE'),
        file(credentialsId: 'call-service.env', variable: 'CALL_ENV_FILE')
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
            echo "${script.DOCKER_PASSWORD}" | docker login \
                --username "${script.DOCKER_USER}" \
                --password-stdin
        '''
                }
    }

    def pushImage(){
        script.withCredentials([
        file(credentialsId: 'auth-service.env', variable: 'AUTH_ENV_FILE'),
        file(credentialsId: 'user-service.env', variable: 'USER_ENV_FILE'),
        file(credentialsId: 'captain-service.env', variable: 'CAPTAIN_ENV_FILE'),
        file(credentialsId: 'ride-service.env', variable: 'RIDE_ENV_FILE'),
        file(credentialsId: 'payment-service.env', variable: 'PAYMENT_ENV_FILE'),
        file(credentialsId: 'call-service.env', variable: 'CALL_ENV_FILE')
    ]) {
        script.sh '''
            cd Micro-Services
            docker compose push
        '''
    }
    }
    
}