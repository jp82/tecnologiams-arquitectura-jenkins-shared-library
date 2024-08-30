package pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.osv

class ScannerUtil implements Serializable {
    def script
    ScannerUtil(script) {
        this.script = script
    }

    def scanner(String artifactoryBaseUrl, String registryDomain, String artifactoryCredentialsId, String registryCredentialsId) {
        this.script.withCredentials([this.script.usernamePassword(credentialsId: artifactoryCredentialsId, usernameVariable: 'artifactoryUser', passwordVariable: 'artifactoryPassword'), this.script.usernamePassword(credentialsId: registryCredentialsId, usernameVariable: 'registryUser', passwordVariable: 'registryPassword')]) {
            this.script.sh "ls -lia"
            this.script.sh "./gradlew -v -Dgradle.wrapperUser=${this.script.artifactoryUser} -Dgradle.wrapperPassword=${this.script.artifactoryPassword}"
            this.script.sh "./gradlew resolveAndLockAll --write-locks -PartifactoryUrl=${artifactoryBaseUrl} -PartifactoryUsername=${this.script.artifactoryUser} -PartifactoryPassword=${this.script.artifactoryPassword}  -PregistryUrl=${registryDomain} -PregistryUsername=${this.script.registryUser} -PregistryPassword=${this.script.registryPassword}"
            this.script.sh "tree"
        }   
        this.script.sh "osv-scanner --experimental-local-db --lockfile=./gradle.lockfile --lockfile=./buildscript-gradle.lockfile --format table"
    }
}
