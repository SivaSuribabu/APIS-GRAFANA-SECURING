1. First clone the repositories in which you want to find the apis.
    **git clone --branch <branch-name> --single-branch <repo-url>**
    **git clone --branch  main --single-branch sivasuribabu@github.com**
    **git clone --branch main --single-branch sivasuribabu@github.com --depth 1**


**Step-by-step: Identify the framework in your project**
Open pom.xml and look for these clues:
✅ If you see this:
<parent>
    <groupId>org.springframework.boot</groupId>
➡️ Then it’s Spring Boot

✅ If you see:
<dependency>
    <groupId>org.springframework</groupId>
➡️ Then it’s Spring (XML-based or classic)

✅ If you see:
<dependency>
    <groupId>org.springframework</groupId>
➡️ Then it’s Spring (XML-based or classic)

✅ If you see:
<groupId>org.apache.cxf</groupId>
➡️ Then it’s Apache CXF (SOAP or REST)



1. grep -R "url" -n .
2. grep -R "WebClient" -n .
3. grep -R "RestTemplate" -n .
4. grep -R "http://" -n .
5. grep -R "https://" -n .
6. grep -R "endpoint" -n .
