

static void main(String[] args) {


	Set aFiles = new HashSet();
	Set mFiles = new HashSet();
	Set dFiles = new HashSet();

// git diff --name-only HEAD~3 HEAD
	//gets all files added/modified/deleted by commit, newer commits at the top
	def gitlog = "git log origin/master.. --name-status --oneline --no-merges".execute().text
	gitlog.split( '\n' ).findAll { 
		if (it.startsWith("D\t")) {
			def dFile = it.substring(2, it.size())
			if (!aFiles.contains(dFile) && !mFiles.contains(dFile)) {
				//if file was not added or modified in a newer commit
				dFiles.add(dFile)
			}
		} 
		if (it.startsWith("A\t")) {
			def aFile = it.substring(2, it.size())
			if (!mFiles.contains(aFile) && !dFiles.contains(aFile)) {
				//if file was not modified or deleted in a newer commit
				aFiles.add(aFile)
			}
		} 
		if (it.startsWith("M\t")) {
			def mFile = it.substring(2, it.size())
			if (!dFiles.contains(mFile)) {
				//if file was not deleted in a newer commit
				mFiles.add(mFile)
			}
		} 
	}


	println "A"
	aFiles.forEach { println it}
	println "M"
	mFiles.forEach { println it}


	// println gitlog
	// def proc = "git log origin/master.. --name-status --oneline --no-merges".execute()
	// def b = new StringBuffer()
	// proc.consumeProcessErrorStream(b)

	// println proc.text
	// println b.toString()

	// def output = "java -jar <jar file> <options>".execute().text 
// git log origin/master.. --name-status --oneline --no-merges

// //loop from bottom of output

// if A 
// 	add alist
// if M
// 	if in alist
// 		remove from alist
// 	add to mlist

}