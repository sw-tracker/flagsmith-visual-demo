# Email: boss@boss.com
# Password: Admin123!
# API Key: wuyCglzN.53Y57ie9L0B3Vmope97aFmKTV3v6AWeY
terraform {
  required_providers {
    flagsmith = {
      source = "Flagsmith/flagsmith"
      # curl https://registry.terraform.io/v1/providers/flagsmith/flagsmith/versions
      # rm -rf .terraform .terraform.lock.hcl
      version = "0.7.0" # or whatever the latest version is
    }
  }
}

provider "flagsmith" {
  # or omit this for master_api_key to be read from environment variable
  master_api_key = "wuyCglzN.53Y57ie9L0B3Vmope97aFmKTV3v6AWeY"
  # export FLAGSMITH_MASTER_API_KEY="wuyCglzN.53Y57ie9L0B3Vmope97aFmKTV3v6AWeY"
#   base_url = "http://localhost:8000/api/v1"
#   master_api_key = "399abe9eaf545a95b2d66384effdf208ebf491fc"
}

# curl -H "Authorization: Api-Key wuyCglzN.53Y57ie9L0B3Vmope97aFmKTV3v6AWeY" http://localhost:8000/api/v1/projects/
