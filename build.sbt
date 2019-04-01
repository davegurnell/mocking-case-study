scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.scalactic"          %% "scalactic"     %  "3.0.4" % Test,
  "org.scalatest"          %% "scalatest"     %  "3.0.4" % Test,
  "org.scalamock"          %% "scalamock"     %  "4.1.0" % Test,
  "org.scalacheck"         %% "scalacheck"    % "1.14.0" % Test,
  "org.mockito"            %% "mockito-scala" %  "1.2.4" % Test,
  "com.github.tomakehurst"  % "wiremock"      % "2.22.0" % Test,
)
