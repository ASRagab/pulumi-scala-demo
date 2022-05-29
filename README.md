## Pulumi Scala Demo

### Prerequisties

- JDK 11 or later
- [Pulumi cli](https://www.pulumi.com/docs/get-started/install) installed
- Pulumi account created
- AWS account and credentials configured for use on the command line (see: [configuring aws](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-configure.html))

### Usage

#### Assemble JAR

```bash
> sbt ";clean;assembly`
```

#### Pulumi Up

From the root directory:

```bash
> pulumi up
```

It should run you through an account creation process, and download some plugins.

#### Pulumi Destroy

```bash
> pulumi destroy
```
