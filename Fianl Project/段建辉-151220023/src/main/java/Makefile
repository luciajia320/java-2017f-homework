JAR_PKG = Huluwa.jar
ENTRY_POINT = Square
RES_DIR = no
SOURCE_FILES = creature.java MyExceptions.java \
		creature.java Position.java \
		huluwas.java grandfather.java moster.java \
		Formation.java \
		sort.java sortImp.java Square.java

JAVAC = javac
JFLAGS = -encoding UTF-8

# make build: 编译，在bin目录下生成 java classes。
# make clean: 清理编译结果，以便重新编译
# make rebuild: 清理编译结果，重新编译。
# make run: make 之后，可以通过make run查看运行结果。
# make jar: 生成可执行的jar包。

vpath %.class
vpath %.java

Default:
	@echo "make new: new project, create src, bin, res dirs."
	@echo "make build: build project."
	@echo "make clean: clear classes generated."
	@echo "make rebuild: rebuild project."
	@echo "make run: run your app."
	@echo "make jar: package your project into a executable jar."

build: $(SOURCE_FILES:.java=.class)

# pattern rule
%.class: %.java
	$(JAVAC) -cp -d $(JFLAGS) $<

rebuild: clean build

.PHONY: new clean run jar

new:
ifeq ($(RES_DIR),yes)
	mkdir -pv src bin res
else
	mkdir -pv src bin
endif

clean:
	rm -frv *.class

run:
	java -cp bin $(ENTRY_POINT)

jar:
ifeq ($(RES_DIR),yes)
	jar -cvfe $(JAR_PKG) $(ENTRY_POINT)  -C bin . res
else
	jar -cvfe $(JAR_PKG) $(ENTRY_POINT) -C bin .
endif
