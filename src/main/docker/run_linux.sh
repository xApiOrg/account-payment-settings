kill $(cat /tmp/instance.pid)
java -jar ../@project.build.finalName@.jar 1> out.log &
echo $! > /tmp/instance.pid