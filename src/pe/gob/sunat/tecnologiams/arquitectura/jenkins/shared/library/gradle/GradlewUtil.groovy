package pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.gradle

class GradlewUtil implements Serializable {
    def script
    GradlewUtil(script) {
        this.script = script
    }
	
	def setDefaultJavaVersion(){
        this.script.sh "java -version"
        def DEFAULT_JAVA_VERSION = this.script.sh(script: $/
            printenv DEFAULT_JAVA_VERSION || echo '-'
          /$, returnStdout: true).trim()
        
        if (DEFAULT_JAVA_VERSION != null && !DEFAULT_JAVA_VERSION.isEmpty() && DEFAULT_JAVA_VERSION != '-') {
            this.script.env.JAVA_HOME=DEFAULT_JAVA_VERSION
            this.script.sh "echo \"JAVA_HOME=\$JAVA_HOME\""
        }
    }
	
    def cleanAndBootJar(String extraArgs, String artifactoryBaseUrl, String registryDomain, String artifactoryCredentialsId, String registryCredentialsId) {
        setDefaultJavaVersion()
		this.script.withCredentials([this.script.usernamePassword(credentialsId: artifactoryCredentialsId, usernameVariable: 'artifactoryUser', passwordVariable: 'artifactoryPassword'), this.script.usernamePassword(credentialsId: registryCredentialsId, usernameVariable: 'registryUser', passwordVariable: 'registryPassword')]) {
            this.script.sh "chmod +777 gradlew"
            this.script.sh "ls -lia"
            this.script.sh "./gradlew -v -Dgradle.wrapperUser=${this.script.artifactoryUser} -Dgradle.wrapperPassword=${this.script.artifactoryPassword}"
            this.script.sh "./gradlew clean bootJar ${extraArgs} -PartifactoryUrl=${artifactoryBaseUrl} -PartifactoryUsername=${this.script.artifactoryUser} -PartifactoryPassword=${this.script.artifactoryPassword}  -PregistryUrl=${registryDomain} -PregistryUsername=${this.script.registryUser} -PregistryPassword=${this.script.registryPassword} -PsunatPUrepo=${artifactoryBaseUrl} -PsunatPUrepoUser=${this.script.artifactoryUser} -PsunatPUrepoPassword=${this.script.artifactoryPassword}"
            this.script.sh "tree"
        }
    }

    def runScript(String args, String artifactoryBaseUrl, String registryDomain, String artifactoryCredentialsId, String registryCredentialsId) {
        setDefaultJavaVersion()
		this.script.withCredentials([this.script.usernamePassword(credentialsId: artifactoryCredentialsId, usernameVariable: 'artifactoryUser', passwordVariable: 'artifactoryPassword'), this.script.usernamePassword(credentialsId: registryCredentialsId, usernameVariable: 'registryUser', passwordVariable: 'registryPassword')]) {
            this.script.sh "chmod +777 gradlew"
            this.script.sh "ls -lia"
            this.script.sh "./gradlew -v -Dgradle.wrapperUser=${this.script.artifactoryUser} -Dgradle.wrapperPassword=${this.script.artifactoryPassword}"
            this.script.sh "./gradlew ${args} -PartifactoryUrl=${artifactoryBaseUrl} -PartifactoryUsername=${this.script.artifactoryUser} -PartifactoryPassword=${this.script.artifactoryPassword} -PregistryUrl=${registryDomain} -PregistryUsername=${this.script.registryUser} -PregistryPassword=${this.script.registryPassword} -PsunatPUrepo=${artifactoryBaseUrl} -PsunatPUrepoUser=${this.script.artifactoryUser} -PsunatPUrepoPassword=${this.script.artifactoryPassword}"
            this.script.sh "tree"
            this.script.sh "docker images"
            //this.script.sh "docker rmi -f \$(docker images | grep \$(grep \"rootProject.name\" settings.gradle | awk '{print \$3}' | tr -d \"'\")) 2> /dev/null || true"
            //this.script.sh "docker images"
        }
    }
}
