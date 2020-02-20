pipeline {
   agent any

   stages {
      stage('Build') {
         steps {
            sh 'sudo chmod +x gradlew'
            sh 'sudo ./gradlew build'
            }
         }
      stage('Test Etsy frontend'){
          steps{
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    sh 'sudo ./gradlew cucumber'
                }
            }

      }
      stage('Test Github gists api'){
          steps{
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    withCredentials([usernamePassword(credentialsId: '2b8a3556-db9e-4b25-bf39-0d27faceb9d6', passwordVariable: 'password', usernameVariable: 'username')]) {
                        sh 'sudo ./gradlew api_cucumber -Pusername=$username -Ppassword=$password'
                    }
                }

            }

      }

   }
   post {
        always {
            cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/cucumber.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
        }
    }
}