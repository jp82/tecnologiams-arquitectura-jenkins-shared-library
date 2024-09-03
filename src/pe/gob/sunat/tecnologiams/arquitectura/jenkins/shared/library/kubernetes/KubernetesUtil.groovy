package pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.kubernetes

class KubernetesUtil implements Serializable {
    def script
    KubernetesUtil(script) {
        this.script = script
    }
    def runScript(String args, String kubeconfigCredentialsId) {
        this.script.withCredentials([this.script.file(credentialsId: kubeconfigCredentialsId, variable: 'kubeconfig')]) {
            this.script.sh "mkdir -p ~/.kube"
            this.script.sh "rm -rf ~/.kube/config"
            this.script.sh "cp \$kubeconfig ~/.kube/config"
            this.script.sh "kubectl config get-contexts"
            this.script.sh "${args}"
        }
    }
}

