# flags.tf
resource "flagsmith_feature" "new_standard_feature" {
  feature_name = "new_standard_feature"
  project_uuid = "420e5201-7692-4e68-b828-9aa6a9d37c8e"
  description  = "This is a new standard feature"
  type         = "STANDARD"
}
