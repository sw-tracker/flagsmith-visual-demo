# flags.tf
resource "flagsmith_feature" "dark_mode" {
  project_id = flagsmith_project.main.id
  name       = "dark_mode"
  description = "Enable dark mode feature"
}

resource "flagsmith_environment" "production" {
  project_id = flagsmith_project.main.id
  name       = "Production"
}

resource "flagsmith_feature_state" "dark_mode_prod" {
  environment_id = flagsmith_environment.production.id
  feature_id     = flagsmith_feature.dark_mode.id
  enabled        = true
}
