# Help command
help:
	@echo "Available commands:"
	@echo "  make j-all          - JavaFX App: Compiles and starts multiple instances of the application"
	@echo "  make j-start        - JavaFX App: Compiles and starts one instance of the application"
	@echo "  make j-build    		 - JavaFX App: Build"
	@echo "  make j-run      		 - JavaFX App: Runs one instance of the application"
	@echo "  make fs-start       - Flagsmith: Initialize Flagsmith containers and Terraform"
	@echo "  make fs-apply       - Flagsmith: Apply Terraform configuration"
	@echo "  make fs-destroy     - Flagsmith: Destroy Terraform resources and stop containers"
	@echo "  make fs-status      - Flagsmith: Show status of containers and Terraform state"
	@echo "  make flipt-start    - Flipt: Start Flipt containers"
	@echo "  make help           - Show this help message"

# JavaFX App Commands
j-all:
	chmod +x startDemo.sh
	./startDemo.sh

j-start: j-build j-run

j-build:
	mvn compile package

j-run:
	java -jar shade/hellofx.jar

# Flagsmith commands
fs-start:
	cd flagsmith && docker compose up
	sleep 10
	cd flagsmith && terraform init

fs-apply:
	cd flagsmith && terraform apply

fs-destroy:
	cd flagsmith && terraform destroy

fs-status:
	cd flagsmith && docker compose ps
	cd flagsmith && terraform show

# Flipt commands
flipt-start:
	cd flipt && docker compose up
