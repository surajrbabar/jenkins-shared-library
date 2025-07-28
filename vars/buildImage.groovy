#!/user/bin/env groovy

def call(String imageName){
    echo "building the docker image ..."
    withCredentials([usernamePassword(credentialsId : 'dockerhub-credentials', usernameVariable : 'USER', passwordVariable : 'PASS')]){
        sh "docker build -t $imageName ."
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh "docker push $imageName"
    }
}
