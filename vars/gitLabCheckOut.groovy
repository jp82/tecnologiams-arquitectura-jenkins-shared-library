import pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.gitlab.GitLabUtil

void call(script, String repoUrl, String branchName, String defaultCredentialsId="gitlab-jenkins"){
  println "gitLab: CheckOut()"
  def git = new GitLabUtil(script)
  git.checkoutFrom(repoUrl, branchName, defaultCredentialsId)
}
