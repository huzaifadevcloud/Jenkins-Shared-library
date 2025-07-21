def call(String imageName, String imageTag) {
    echo "Starting deployment using image: ${imageName}:${imageTag}"

    // Run container and expose port 81015
    sh """
        docker run --rm -p 8105:8105 ${fullImage}
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
