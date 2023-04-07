# If tomcat directory does not exist, create it
if [ ! -d "tomcat" ]; then
  mkdir tomcat/
fi

# Copy server from local directory to current project root
# and remove already present deploy directory and war archive
cp -r /home/$USER/projects-servers/tomcat-10.1.7/. tomcat/
rm -rf tomcat/projectname-webapp/
rm -f tomcat/projectname-webapp.war

# Create project war archive and copy it to tomcat webapps directory
mvn clean package
cp target/projectname-webapp.war tomcat/webapps/

# Remove previous docker container and image
docker rm -f projectname-webapp-container
docker rmi -f projectname-webapp-image

# Build new docker image and run container
docker build -t projectname-webapp-image .
docker run --name projectname-webapp-container -d -p 8080:8080 projectname-webapp-image
