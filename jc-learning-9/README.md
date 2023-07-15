#java 9 features:
Modules:
A module is a set of packages designed for reuse. 
    Features:
        - Strong encapsulation.
            - module-info.java
                module {module-name} {
                    exports {package} (Defines packages whose public classes can be accessed out side the {module-name} module.)
                    }
e.g java.base module in java 9
    module java.base{
        exports java.lang;
        exports java.io;
        exports java.net;
        exports java.util;
    }
A Module is set of exported and concealed packages.
Accessibility:
    - public to everyone
    - public but only to friend modules
    - public only within a module
    - protected 
    - package
    - private

module-info.java
    module {module-name} {
        exports {package} (Defines packages whose public classes can be accessed out side the {module-name} module.)
        requires {package}    
    }

Benefits of Modules
- Run with  java -p {dir} -m {module-name}
- No missing dependencies
- No cyclic dependencies
- No split packages

Modules are not mandatory- classpath still exists

Reference: Alex Buckely lecture