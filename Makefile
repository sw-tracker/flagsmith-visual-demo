# Help command
help:
	@echo "Available commands:"
	@echo "  make j-all           - JavaFX App: Compiles and starts multiple instances of the application"
	@echo "  make j-start         - JavaFX App: Compiles and starts one instance of the application"
	@echo "  make j-build    	    - JavaFX App: Build"
	@echo "  make j-run      			- JavaFX App: Runs one instance of the application"
	@echo "  make flipt-start     - Flipt: Start Flipt containers"
	@echo "  make flipt-stop      - Flipt: Stop Flipt containers"
	@echo "  make flipt2-start    - Flipt 2.0: Start Flipt 2.0 containers"
	@echo "  make flipt2-stop     - Flipt 2.0: Stop Flipt 2.0 containers"
	@echo "  make fs-start        - Flagsmith: Start Flagsmith containers"
	@echo "  make fs-stop         - Flagsmith: Stop Flagsmith containers"
	@echo "  make ul-start        - Unleash: Start Unleash containers"
	@echo "  make ul-stop         - Unleash: Stop Unleash containers"
	@echo "  make ff-status       - Feature flags: Show status of all running containers"
	@echo "  make help           	- Show this help message"

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
	docker compose --profile flagsmith up -d

fs-stop:
	docker compose --profile flagsmith down

# Flipt commands
flipt-start:
	docker compose --profile flipt up -d

flipt-stop:
	docker compose --profile flipt down

# Flipt 2.0 commands
flipt2-start:
	docker compose --profile flipt2 up -d

flipt2-stop:
	docker compose --profile flipt2 down

# Unleash commands
ul-start:
	docker compose --profile unleash up -d

ul-stop:
	docker compose --profile unleash down

# Feature flag status (across all profiles)
ff-status:
	docker compose --profile flipt --profile flipt2 --profile flagsmith --profile unleash ps
