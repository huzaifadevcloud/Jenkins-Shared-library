def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Missing imageName")
    def imageTag  = config.imageTag ?: 'latest'

    echo "Starting deployment using image: ${imageName}:${imageTag}"

    sh """
        docker run --rm -p 8105:8000 ${imageName}:${imageTag}
    """

    // Optional: Test route and stop container manually if needed
    // If using `-d`, then you can test and stop it like this:
    /*
    sh '''
        docker run -d --name django-notes-app -p 8105:8105 ${fullImage}
        sleep 5
        curl -f http://localhost:8000/demo || echo "Server did not respond"
        docker stop django-notes-app
        docker rm django-notes-app
    '''
    */
}
