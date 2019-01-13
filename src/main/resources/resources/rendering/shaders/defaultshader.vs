#version 330 core

layout (location=0) in vec3 position;
layout (location=1) in vec3 inColor;

out vec3 outColor;

uniform mat4 matrixProjection;
uniform mat4 matrixModelView;

void main(){
	gl_Position = matrixProjection * matrixModelView * vec4(position, 1.0);
	outColor = inColor;
}