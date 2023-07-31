import os
import sys

def generate_json_files(template_dir, output_dir, argument_data):
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    for root, _, filenames in os.walk(template_dir):
        for filename in filenames:
            if filename.endswith(".json"):
                template_path = os.path.join(root, filename)
                relative_path = os.path.relpath(template_path, template_dir)
                output_path = os.path.join(output_dir, replace_arguments_in_string(relative_path, argument_data))
                generate_json_from_template(template_path, output_path, argument_data)

def generate_json_from_template(template_path, output_path, argument_data):
    print(f"generating from {template_path} as {output_path}")

    with open(template_path, "r") as template_file:
        template_content = template_file.read()

    template_content = replace_arguments_in_string(template_content, argument_data)

    with open(output_path, "w") as output_file:
        output_file.write(template_content)

def replace_arguments_in_string(input_string, argument_data):
    for key, value in argument_data.items():
        input_string = input_string.replace("[" + key + "]", str(value))
    return input_string

if __name__ == "__main__":
    template_dir = "templates"
    output_dir = "../src/main/resources/assets/brickmod"

    # Replace these argument values with your own data
    argument_data = {
        "id": sys.argv[1],
    }

    generate_json_files(template_dir, output_dir, argument_data)
