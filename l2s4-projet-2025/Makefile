JC = javac

# Folders
SRC = src
TST = tests
CLS = classes
DOC = docs
JAR = jar

# Files
SOURCES_FILES := $(shell find $(SRC) -name "*.java")
TESTS_FILES := $(shell find $(TST) -name "*.java")

.PHONY: all cls tests run_tests clean doc ares.jar ares-random.jar demeter.jar demeter-random.jar

all : cls

cls:
	mkdir -p $(CLS)
	$(JC) $(SOURCES_FILES) -d $(CLS)

tests:
	mkdir -p $(CLS)
	$(JC) -classpath junit-console.jar:$(CLS) $(TESTS_FILES) -d $(CLS)

run_tests:
	java -jar junit-console.jar -classpath test:$(CLS) -scan-classpath

clean:
	rm -rf $(CLS)/*
	rm -rf $(DOC)/*

doc:
	mkdir -p $(DOC)
	javadoc -d $(DOC) $(SOURCES_FILES)

ares.jar: cls
	mkdir -p $(JAR)
	jar cvfe $(JAR)/ares.jar Essential.Livrable4Ares -C $(CLS) ./

ares-random.jar: cls
	mkdir -p $(JAR)
	jar cvfe $(JAR)/ares-random.jar Essential.Livrable4AresAuto -C $(CLS) ./

demeter.jar: cls
	mkdir -p $(JAR)
	jar cvfe $(JAR)/demeter.jar Essential.Livrable4Demeter -C $(CLS) ./

demeter-random.jar: cls
	mkdir -p $(JAR)
	jar cvfe $(JAR)/demeter-random.jar Essential.Livrable4DemeterAuto -C $(CLS) ./

