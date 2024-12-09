@echo off

echo "reiniciando minikube"
minikube start --driver=hyperv

kubectl rollout restart deployment configservice
echo "configservice reiniciado, abra otra ventana de powershell con administrador, obtenga el pod name (kubectl get pods) y luego revise el log del configservice (kubectl logs <pod-name>)"
pause

kubectl rollout restart deployment eurekaservice
echo "eurekaservice reiniciado, abra otra ventana de powershell con administrador, obtenga el pod name (kubectl get pods) y luego revise el log del eurekaservice (kubectl logs <pod-name>)"
pause

kubectl rollout restart deployment gateway-service
echo "reiniciado gateway-service"

kubectl rollout restart deployment loanevaluation-db-deployment
echo "reiniciado loanevaluation-db-deployment"

kubectl rollout restart deployment register-db-deployment
echo "reiniciado register-db-deployment"

kubectl rollout restart deployment simulation-db-deployment
echo "reiniciado simulation-db-deployment"

kubectl rollout restart deployment solicitude-db-deployment
echo "reiniciado solicitude-db-deployment"

kubectl rollout restart deployment register-db-deployment
echo "reiniciado register-db-deployment"

kubectl rollout restart deployment tracking-db-deployment
echo "reiniciado tracking-db-deployment"


kubectl rollout restart deployment loanevaluation
echo "reiniciado loanevaluation"

kubectl rollout restart deployment register
echo "reiniciado register"

kubectl rollout restart deployment simulate
echo "reiniciado simulate"

kubectl rollout restart deployment solicitude
echo "reiniciado solicitude"

kubectl rollout restart deployment register
echo "reiniciado register"

kubectl rollout restart deployment tracking
echo "reiniciado tracking"

kubectl rollout restart deployment frontend
echo "reiniciado frontend"