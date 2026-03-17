1. First clone the repositories in which you want to find the apis.
    **git clone --branch <branch-name> --single-branch <repo-url>**
    **git clone --branch  main --single-branch sivasuribabu@github.com**
    **git clone --branch main --single-branch sivasuribabu@github.com --depth 1**

2. grep -R "url" -n .
3. grep -R "WebClient" -n .
4. grep -R "RestTemplate" -n .
5. grep -R "http://" -n .
6. grep -R "https://" -n .
7. grep -R "endpoint" -n .
