File file = new File("examplelog.txt")
def array = file as String[]
def regex = "12345"
def firstIndex = -1
def lastIndex = -1
for(int i = 0;i<array.size();i++) {
	def match = array[i] =~ regex
	if(match) {
		if(firstIndex == -1) {
			firstIndex = i;
		}
		else {
			lastIndex = i;
		}
	}
}
def timeRegex = /[\d]+\:[\d]+/
def match = array[firstIndex] =~ timeRegex
String time = match[0].toString()
int t = Integer.parseInt(time[-2..-1]) - 2
String startTime = time[0..2]+t.toString()
match = array[lastIndex] =~ timeRegex
time = match[0].toString()
t = Integer.parseInt(time[-2..-1]) + 2
String lastTime = time[0..2]+t.toString()
for(int i = 0;i<array.size();i++) {
	match = array[i] =~ startTime
	if(match) {
		firstIndex = i;
	}
	match = array[i] =~ lastTime
	if(match) {
		lastIndex = i;
	}
}
File newFile = new File("newlog.txt")
for(int i = firstIndex;i<=lastIndex;i++) {
	newFile << array[i]+"\n"
}