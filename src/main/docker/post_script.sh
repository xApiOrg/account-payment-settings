touch it.works
kill $(cat /tmp/instance.pid)
java -jar ../@project.build.finalName@.jar 1> /dev/null &
echo $! > /tmp/instance.pid