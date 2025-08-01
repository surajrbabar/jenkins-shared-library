#!/user/bin/env groovy

package com.example

class Docker implements Serializable{
    def script

    Docker(script){
        this.script = script
    }

    def buildDockerImage(String imageName){
        script.echo "building the docker image ..."
        script.sh "docker build -t $imageName ."

    }

    def dockerLogin(){
        script.withCredentials([script.usernamePassword(credentialsId : 'dockerhub-credentials', usernameVariable : 'USER', passwordVariable : 'PASS')]){
            script.sh "echo ${script.PASS} | docker login -u ${script.USER} --password-stdin"
        }
    }

    def pushImage(String imageName){
        script.sh "docker push $imageName"
    }

}