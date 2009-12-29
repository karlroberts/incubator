package com.owtelse.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Helper class that holds the config necessary for Configurator to merge velocity templates and properties.
 * The Simplest cases involve passing in details of template and properties. More complex envolve passing in
 * details of a directory where templates are found and a directory where properties are found along with
 * a path to walk.
 */
public class TemplateConfig {

    /**
     * Name of a velocity template file
     */
    private List<String> templates = new ArrayList<String>();

    /**
     *
     */
    private File templateDir = null;

    /**
     * Properties of substitution variables. If set these will override any that are set in any substitution files.
     */
    private Properties substitutions = new Properties();

    /**
     * List of Filenames containing substitution properties
     */
    private List<String> substitutionFiles = new ArrayList<String>();

    /**
     * Alternatively to substitutionFiles a Directory containing a tree of substitution files can be specified.
     * If used then the substition path must be used to let the algorithm find the files in that path.
     * Files will be read from the substitutionDir down to the leafs.
     */
    private File substitutionDir = null;

    private List<String> substitutionPath = new ArrayList<String>();

    /**
     * Specifically named output file.
     */
    private List<File> outputFiles = null;


    /**
     * output directory. If no specific outputFile is specified files will be created in this dir with the same names and relative positioning
     * as files found in templateDir
     */
    private File outputDir = null;
    private boolean validated =false;

    /* ------------------------------------------------------------- */
    /**
     * Constructor allowing individual templates and output files.
     * @param templates
     * @param substitutionFiles
     * @param substitutions
     * @param outputFiles Num of output files must match num of templates or else the lesser one will dictate how many merges are done
     */
    public TemplateConfig(List<String> templates, List<String> substitutionFiles, Properties substitutions, List<File> outputFiles) {
        this.templates.addAll(templates);
        this.substitutionFiles.addAll(substitutionFiles);
        this.substitutions.putAll(substitutions);
        this.outputFiles = outputFiles;

        if (!validated) validateProperties();
    }

    /* ------------------------------------------------------------- */
    /**
     * Constructor allowing a template dir and an outputDir
     * @param templateDir is a directory that is recursivly searched for templates
     * @param substitutionFiles
     * @param substitutions
     * @param outputDir is a directory that merged templates are written to. The directory hirarchy will be maintained
     */
    public TemplateConfig(File templateDir, List<String> substitutionFiles, Properties substitutions, File outputDir) {
        this.templateDir = templateDir;
        this.substitutionFiles.addAll(substitutionFiles);
        this.substitutions.putAll(substitutions);
        this.outputDir = outputDir;

        if (!validated) validateProperties();
    }

    /* ------------------------------------------------------------- */
    /**
     * Constructor allowing a template dir and an outputDir
     * @param templateDir is a directory that is recursivly searched for templates
     * @param substitutionDir
     * @param substitutions
     * @param outputDir is a directory that merged templates are written to. The directory hirarchy will be maintained
     * @param substitutionPath
     */
    public TemplateConfig(File templateDir, File substitutionDir, Properties substitutions, File outputDir, List<String> substitutionPath) {
        this.templateDir = templateDir;
        this.substitutionDir = substitutionDir;
        this.substitutions.putAll(substitutions);
        this.outputDir = outputDir;
        this.substitutionPath.addAll(substitutionPath);

        if (!validated) validateProperties();
    }

    /* ------------------------------------------------------------- */
    /**
     * Constructor used by Frameworks eg Spring or Maven. When used in this manner the framework must
     * specify the various properties in ways that match the the previous constructors
     * before the Template config is used for the first time.
     */
    public TemplateConfig() {

    }


    /**
     * Used to force compliance with the contructor Rules.
     * @throws IllegalArgumentException
     */
    private void validateProperties() {
        if ((this.templates.size() == 0) && templateDir == null) {
            throw new IllegalArgumentException("Must set a non emty list of templates or a templateDir");
        }
        if ((substitutionDir != null) && substitutionPath.isEmpty()) {
            throw new IllegalArgumentException("If you have set a substitutionDir you must set a path to navigate from that dir to find all files that will be used as substitutionFiles");
        }
        if ((this.outputFiles.size() == 0) && outputDir ==null) {
            throw new IllegalArgumentException("Must set a non emty list of output files or an outputDir");
        }

        this.validated=true;
    }

    public List<String> getTemplates() {
        if (!validated) validateProperties();
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }

    public File getTemplateDir() {
        if (!validated) validateProperties();
        return templateDir;
    }

    public void setTemplateDir(File templateDir) {
        this.templateDir = templateDir;
    }

    public Properties getSubstitutions() {
        if (!validated) validateProperties();
        return substitutions;
    }

    public void setSubstitutions(Properties substitutions) {
        this.substitutions = substitutions;
    }

    public List<String> getSubstitutionFiles() {
        if (!validated) validateProperties();
        return substitutionFiles;
    }

    public void setSubstitutionFiles(List<String> substitutionFiles) {
        this.substitutionFiles = substitutionFiles;
    }

    public File getSubstitutionDir() {
        if (!validated) validateProperties();
        return substitutionDir;
    }

    public void setSubstitutionDir(File substitutionDir) {
        this.substitutionDir = substitutionDir;
    }

    public List<String> getSubstitutionPath() {
        if (!validated) validateProperties();
        return substitutionPath;
    }

    public void setSubstitutionPath(List<String> substitutionPath) {
        this.substitutionPath = substitutionPath;
    }

    public List<File> getOutputFiles() {
        if (!validated) validateProperties();
        return outputFiles;
    }

    public void setOutputFiles(List<File> outputFiles) {
        this.outputFiles = outputFiles;
    }

    public File getOutputDir() {
        if (!validated) validateProperties();
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }
}
