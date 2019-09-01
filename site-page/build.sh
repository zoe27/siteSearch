con=$1
port=$2
port1=$3
echo $con"  ".$port."  ".$port1
docker stop $con
docker rm $con
docker rmi $con
docker images
docker build -t $con .
docker images
docker run -d --name $con -p $port:$port1 $con
docker logs -f $con