# provider.tf
terraform {
  required_providers {
    flagsmith = {
      source = "flagsmith/flagsmith"
      version = "~> 1.0"
    }
  }
}

provider "flagsmith" {
  api_url = "http://localhost:8000/api/v1"
  api_key = var.flagsmith_api_key
}
