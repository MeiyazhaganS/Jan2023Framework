pipeline{
    agent any
    
    stages{
        stage("Build"){
            steps{
                echo("Build Project")
            }
        }    
        stage("Run UT"){
            steps{
                echo("Run unit test cases")
            }
        }
        stage("Deploy to Dev"){
            steps{
                echo("Dev deployment")
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("QA deployment")
            }
        }
        stage("Run Automation Regression Test"){
            steps{
                echo("Running automation regression tests")
            }
        }
        stage("Deploy to Stage"){
            steps{
                echo("Stage deployment")
            }
        }
        stage("Run Automation Sanity Test"){
            steps{
                echo("Running automation sanity tests")
            }
        }
        stage("Deploy to Production"){
            steps{
                echo("Prod deployment")
            }
        }
    }
}