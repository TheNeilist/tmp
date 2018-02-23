

static void main(String[] args) {


	Set aFiles = new HashSet();
	Set mFiles = new HashSet();
	Set dFiles = new HashSet();


	def gitlog = "git log origin/master.. --name-status --oneline --no-merges".execute().text
 	def changes = "git diff --name-only HEAD~3 HEAD".execute().text
	println changes

}