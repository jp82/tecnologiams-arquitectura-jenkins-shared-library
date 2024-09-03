import pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.kubernetes.KubernetesUtil

void call(script, String args, String kubeconfigCredentialsId){
  println "************* kubernetes: get kubeconfig ************* "
  def k8s = new KubernetesUtil(script)
  k8s.runScript(args,  kubeconfigCredentialsId)
}
