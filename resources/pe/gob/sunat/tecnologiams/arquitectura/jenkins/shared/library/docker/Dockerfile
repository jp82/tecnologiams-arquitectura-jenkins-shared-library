FROM $(DOCKER_IMAGE)
ARG JAR_FILE=build/libs/*.jar
ENV TZ=America/Lima
COPY ${JAR_FILE} /app.jar
#RUN apk add --no-cache tzdata curl
#Creamos un Link hacia la zona horaria Lima
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java", "-jar", "/app.jar"]
