# RuneScape-Private-Server
A server for the RuneScape v550 client written in Java.

This project is an emulator that aims to replicate RuneScape from back in 2009. This is was a learning project and should not be run as the client is still property of Jagex LTD.
There is a client included with the server.

## Getting Started

### Prerequisites
1. The project uses [Gradle](https://gradle.org) so this must be installed on your machine.
2. Java 8 or higher
3. Port 8080 open for the web server that hosts the client
4. Port 43594 open for the game server

### Installing
1. Run `gradle build` in the RuneScape-Private-Server directory to download dependencies and build the project.

The project should now be ready and setup. You can run the project using the `gradle run` command.

## Contributing
We are happy to have contributions whether it is for small bug fixes or new pieces of major functionality. To contribute changes, you should first fork the upstream repository to your own GitHub account. You can then add a new remove for upstream and rebase any changes to
make keep up to date with upstream.

`git remote add upstream https://github.com/skdev/RuneScape-Private-Server.git`

The style guides the project uses is based on the [Google style guide](https://google.github.io/styleguide/javaguide.html)

## Authors
*[Graham Edgecombe](https://github.com/grahamedgecombe)* - Initial server framework

*Suraj Kumar* - Decompiled the client and handled communication with the server

## Acknowledgements
[Leanbow](https://github.com/leanbow) for refactoring the 550 client and helping to identify packets.

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
