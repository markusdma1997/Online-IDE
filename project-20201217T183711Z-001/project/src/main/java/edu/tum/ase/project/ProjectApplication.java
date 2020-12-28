package edu.tum.ase.project;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.service.ProjectService;
import edu.tum.ase.project.model.SourceFile;
import edu.tum.ase.project.service.SourceFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private SourceFileService sourceFileService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("DataSource = " + dataSource);
		//NOTE: CANNOT BE EXECUTED MORE THAN ONCE
		Project project = projectService.createProject(new Project("my-project1"));
		log.info("ID of saved project = " + project.getId());
		Project p = projectService.findByName("my-project1");
		log.info("ID of queried project = " + p.getId());

		//NOT IN EXERCISE SHEET
		//Temporarily remove project
		//projectService.deleteProject("my-project");

		List<Project> projects = projectService.getProjects();
		log.info("Length of project list = " + projects.size());

		//Some additional test
		SourceFile sourceFile1 = sourceFileService.createSourceFile(new SourceFile(project, "sourcefile1", "java"));
		log.info("ID of saved source code = " + sourceFile1.getId());
		SourceFile sourceFile2 = sourceFileService.createSourceFile(new SourceFile(project, "sourcefile2", "main"));
		log.info("ID of saved source code = " + sourceFile2.getId());
		List<SourceFile> sourceFiles = sourceFileService.getSourceFiles();
		log.info("Length of source file list = " + sourceFiles.size());
	}
}