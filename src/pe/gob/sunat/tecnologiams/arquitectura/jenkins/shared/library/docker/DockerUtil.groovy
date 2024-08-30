package pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.docker

class DockerUtil implements Serializable {
    def script
    DockerUtil(script) {
        this.script = script
    }
    def buildAndPublish(String dockerImageName, String dockerImageTag, String registryDomain, String registryPathRepository, String dockerImageFrom, String registryCredentialsId) {
        this.script.withCredentials([this.script.usernamePassword(credentialsId: registryCredentialsId, usernameVariable: 'registryUser', passwordVariable: 'registryPassword')]) {
            this.script.sh "sed 's/\$(DOCKER_IMAGE)/${dockerImageFrom}/g' Dockerfile > newDockerfile"
            this.script.sh "mv newDockerfile Dockerfile"
            this.script.sh "cat Dockerfile"
            this.script.sh "docker login ${registryDomain} -u ${this.script.registryUser} -p ${this.script.registryPassword}"
            this.script.sh "docker build -t ${registryDomain}/${registryPathRepository}/${dockerImageName}:${dockerImageTag} ."
            this.script.sh "docker images"
            this.script.sh "docker push ${registryDomain}/${registryPathRepository}/${dockerImageName}:${dockerImageTag}"
//            this.script.sh "docker rmi -f \$(docker images ${registryDomain}/${registryPathRepository}/${dockerImageName} -aq) 2> /dev/null || true"
            this.script.sh "docker rmi -f \$(docker images | grep ${dockerImageName}) 2> /dev/null || true"
            this.script.sh "docker images"
            this.script.sh "docker logout"
        }
    }
}

