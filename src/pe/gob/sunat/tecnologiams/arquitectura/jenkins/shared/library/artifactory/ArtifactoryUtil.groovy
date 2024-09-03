/**
* @author      Wilmer Orlando Diaz Guevara (Arquitecto Soluciones)
* @version     1.0
*/

package pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.artifactory

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLConnection

class ArtifactoryUtil implements Serializable {
    def script

    ArtifactoryUtil(script) {
        this.script = script
    }

    def dockerPromote(String artifactoryBaseUrl, String repoKey, String bodyJSON, String artifactoryUserName, String artifactoryPwd, String artifactoryCredentialsId) {

        def uri = artifactoryBaseUrl + "api/docker/$repoKey/v2/promote"
        this.script.println "Url: $uri"
        this.script.println "RepoKey: $repoKey"
        this.script.println "BodyJSON:"
        this.script.println bodyJSON

        if (artifactoryUserName != null && !artifactoryUserName.isEmpty() && artifactoryUserName != '-') {
            sendPostRequest uri, bodyJSON, artifactoryUserName, artifactoryPwd
        }
        else{
            this.script.withCredentials([this.script.usernamePassword(credentialsId: artifactoryCredentialsId, usernameVariable: 'artifactoryUser', passwordVariable: 'artifactoryPassword')]) {
                sendPostRequest uri, bodyJSON, this.script.artifactoryUser, this.script.artifactoryPassword
            }
        }

    }


    def sendPostRequest(String uri, String bodyJSON, String artifactoryUser, String artifactoryPassword) {

        def url = new URL(uri)
        def conn = url.openConnection()
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json")

        def accessToken = "$artifactoryUser:$artifactoryPassword".bytes.encodeBase64().toString()
        conn.setRequestProperty("Authorization", "Basic " + accessToken)
        conn.setDoOutput(true)

        def writer = new OutputStreamWriter(conn.getOutputStream())
        writer.write(bodyJSON)
        writer.flush()

        def code = conn.getResponseCode()
        writer.close()
        this.script.println "POST response status code = $code"


        if (code == 200) {
            this.script.println "*********************** Request Successful ***********************"
            this.script.println "*********************** Response BODY ***********************"
        } else {
            this.script.println "*********************** ERROR AL PROMOCIONAR ***********************"
            this.script.println "*********************** Request Failed ***********************"
        }
        def responseBody = conn.getInputStream().getText()
        this.script.println responseBody

    }

}

