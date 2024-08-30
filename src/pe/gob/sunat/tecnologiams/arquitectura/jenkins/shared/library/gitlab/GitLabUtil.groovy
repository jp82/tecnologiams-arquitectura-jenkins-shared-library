package pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.gitlab

class GitLabUtil implements Serializable {
    def script
    GitLabUtil(script) {
        this.script = script
    }
    def checkoutFrom(String repoUrl, String branchName, String defaultCredentialsId) {
        this.script.git credentialsId: defaultCredentialsId, url: repoUrl, branch: branchName
    }
}
