def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: 'latest'
    def credentials = config.credentials ?: 'docker-hub-credentials'
    def dockerHubUser = config.dockerHubUser ?: 'huzaifafev'

    echo "Pushing Docker image: ${dockerHubUser}/${imageName}:${imageTag}"

    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {
        sh """
            docker tag ${imageName}:${imageTag} ${dockerHubUser}/${imageName}:${imageTag}
            echo "\$DOCKER_PASS" | docker login -u "\$DOCKER_USER" --password-stdin
            docker push ${dockerHubUser}/${imageName}:${imageTag}
        """
    }
}
