# Help command
help:
	@echo "Available commands:"
	@echo "  make j-all          - JavaFX App: Compiles and starts multiple instances of the application"
	@echo "  make j-start        - JavaFX App: Compiles and starts one instance of the application"
	@echo "  make j-build    		 - JavaFX App: Build"
	@echo "  make j-run      		 - JavaFX App: Runs one instance of the application"
	@echo "  make fs-start       - Flagsmith: Initialize Flagsmith containers"
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

# Flipt commands
flipt-start:
	cd flipt && docker compose up
