# Demo for a Services Discovery with Eureka Lite K8s Proxy

Provided that you have a kubernetes cluster and have installed
the [Eureka lite Kubernetes Proxy](https://github.com/Haybu/spring-cloud-k8s-eureka-controller)

The demo has two services, backend-service and frontend-service.
The backend-service is installed as a kubernetes internal service,
and the front-service is installed as an exposed kubernetes service.
The backend-service is enabled (in the manifest) to register with Eureka,
and the frontend-service uses a client-side loadbalancer to call 
a backend-service instances discovered from the Eureka server.

```bash
## deploy the backend service
$ kubectl create -f ./backend-service/deploy/manifest_kubernetes.yaml

## deploy the frontend service
$ kubectl create -f ./frontend-service/deploy/manifest_kubernetes.yaml

## hit the exposed frontend service (http://EXTERNAL-IP) and note that it displays a string message ("Message from backend service!")
```